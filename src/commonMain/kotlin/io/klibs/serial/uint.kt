package io.klibs.serial

/**
 * Returns a [ByteArray] containing the 4 bytes that make up the target `UInt`
 * value.
 *
 * @param be Whether the bytes should be in Big Endian order.
 *
 * Default: `true`
 *
 * @return An array of 4 bytes.
 */
fun UInt.getBytes(be: Boolean = true): ByteArray {
  val out = ByteArray(4)
  unsafeGetBytes(out, 0, be)
  return out
}

/**
 * Fills the given [buffer], starting from the given [offset], with the 4 bytes
 * that make up the target `UInt` value.
 *
 * If the given array's size is less than `offset + 4`, an exception will be
 * thrown.
 *
 * Order is Big Endian.
 *
 * @param buffer `ByteArray` that will be filled with the bytes from the target
 * `UInt`.
 *
 * @param offset Offset position in the [buffer] array to start writing at.
 *
 * Default: `0`
 *
 * @param be Whether the bytes should be written in Big Endian order.
 *
 * Default: `true`
 *
 * @return `4`, the number of bytes written to the buffer.
 */
fun UInt.getBytes(buffer: ByteArray, offset: Int = 0, be: Boolean = true): Int {
  if (offset + 4 >= buffer.size)
    throw Throwable("Writing the value $this to the given buffer would cause it to overflow!")
  unsafeGetBytes(buffer, offset, be)
  return 4
}

/**
 * Returns a [UByteArray] containing the 4 unsigned bytes that make up the
 * target `UInt` value.
 *
 * @param be Whether the bytes should be in Big Endian order.
 *
 * Default: `true`
 *
 * @return An array of 4 unsigned bytes.
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun UInt.getUBytes(be: Boolean = true): UByteArray {
  val out = UByteArray(4)
  unsafeGetBytes(out.asByteArray(), 0, be)
  return out
}

/**
 * Fills the given [buffer], starting from the given [offset], with the 4 bytes
 * that make up the target `UInt` value.
 *
 * If the given array's size is less than `offset + 4`, an exception will be
 * thrown.
 *
 * @param buffer `UByteArray` that will be filled with the bytes from the target
 * `UInt`.
 *
 * @param offset Offset position in the [buffer] array to start writing at.
 *
 * Default: `0`
 *
 * @param be Whether the bytes should be written in Big Endian order.
 *
 * Default: `true`
 *
 * @return `4`, the number of bytes written to the buffer.
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun UInt.getUBytes(buffer: UByteArray, offset: Int = 0, be: Boolean = true) = getBytes(buffer.asByteArray(), offset, be)

private inline fun UInt.unsafeGetBytes(buffer: ByteArray, offset: Int, be: Boolean) {
  if (be) {
    buffer[offset    ] = ((this shr 24) and 0xFFu).toByte()
    buffer[offset + 1] = ((this shr 16) and 0xFFu).toByte()
    buffer[offset + 2] = ((this shr 8 ) and 0xFFu).toByte()
    buffer[offset + 3] = ((this       ) and 0xFFu).toByte()
  } else {
    buffer[offset + 3] = ((this shr 24) and 0xFFu).toByte()
    buffer[offset + 2] = ((this shr 16) and 0xFFu).toByte()
    buffer[offset + 1] = ((this shr 8 ) and 0xFFu).toByte()
    buffer[offset    ] = ((this       ) and 0xFFu).toByte()
  }
}