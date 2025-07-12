package org.overgroup.ctf.listeners

import org.apache.commons.lang3.SerializationUtils
import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataType
import java.io.ByteArrayInputStream
import java.io.IOException
import java.io.ObjectInputStream
import java.util.UUID

class UUIDDataType : PersistentDataType<ByteArray, UUID> {
    override fun getPrimitiveType(): Class<ByteArray> = ByteArray::class.java

    override fun getComplexType(): Class<UUID> = UUID::class.java

    override fun toPrimitive(complex: UUID, context: PersistentDataAdapterContext): ByteArray {
        return SerializationUtils.serialize(complex)
    }

    override fun fromPrimitive(primitive: ByteArray, context: PersistentDataAdapterContext): UUID {
        try {
            val ioStream = ByteArrayInputStream(primitive)
            val objectStream = ObjectInputStream(ioStream)
            return objectStream.readObject() as UUID
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return UUID.randomUUID()
    }
}