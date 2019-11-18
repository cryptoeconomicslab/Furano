package com.cryptoeconomicslab.furano_android.db

import com.cryptoeconomicslab.furano_common.db.BatchOperation
import com.cryptoeconomicslab.furano_common.db.KeyValueStore
import com.cryptoeconomicslab.furano_common.types.Bytes
import org.iq80.leveldb.DB
import org.iq80.leveldb.Options
import org.iq80.leveldb.impl.Iq80DBFactory
import java.io.File


class KeyValueStoreImpl(
    private val dbName: Bytes? = null,
    private val prefix: Bytes = byteArrayOf(),
    private val externalStorageResolver: ExternalStorageResolver
) : KeyValueStore {
    override fun get(key: Bytes): Bytes {
        val db = openDb()
        try {
            return db.get(getKey(key))
        } catch (e: Exception) {
            throw e
        } finally {
            db.close()
        }
    }

    override fun put(key: Bytes, value: Bytes) {
        val db = openDb()
        try {
            db.put(getKey(key), value)
        } catch (e: Exception) {
            throw e
        } finally {
            db.close()
        }
    }

    override fun del(key: Bytes) {
        val db = openDb()
        try {
            db.delete(getKey(key))
        } catch (e: Exception) {
            throw e
        } finally {
            db.close()
        }
    }

    override fun batch(operations: List<BatchOperation>) {
        val db = openDb()
        val batch = db.createWriteBatch()
        try {
            operations.map {
                when (it) {
                    is BatchOperation.PutBatchOperation -> {
                        batch.put(getKey(it.key), it.values)
                    }
                    is BatchOperation.DelBatchOperation -> {
                        batch.delete(getKey(it.key))
                    }
                }
            }
            db.write(batch)
        } catch (e: Exception) {
            throw e
        } finally {
            batch.close()
        }
    }

    override fun iter(bounds: Bytes): Iterator<Map.Entry<Bytes, Bytes>> =
        openDb().iterator().apply {
            this.seek(bounds)
        }

    override fun bucket(key: Bytes): KeyValueStore =
        KeyValueStoreImpl(
            prefix = getKey(key),
            externalStorageResolver = externalStorageResolver
        )

    private fun getKey(key: Bytes): Bytes = prefix + key

    private fun openDb(): DB {
        val options = Options()
        options.createIfMissing(true)
        val externalFile = File(externalStorageResolver.getFilePath())
        return Iq80DBFactory.factory.open(externalFile, options)
    }
}
