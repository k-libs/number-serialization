package io.klibs.serial

/**
 * Returns a [ByteArray] containing the 8 bytes that make up the target `ULong`
 * value.
 *
 * @param be Whether the bytes should be in Big Endian order.
 *
 * Default: `true`
 *
 * @return An array of 8 bytes.
 */
fun ULong.getBytes(be: Boolean = true): ByteArray {
  val out = ByteArray(8)
  unsafeGetBytes(out, 0, be)
  return out
}

/**
 * Fills the given [buffer], starting from the given [offset], with the 8 bytes
 * that make up the target `ULong` value.
 *
 * If the given array's size is less than `offset + 8`, an exception will be
 * thrown.
 *
 * Order is Big Endian.
 *
 * @param buffer `ByteArray` that will be filled with the bytes from the target
 * `ULong`.
 *
 * @param offset Offset position in the [buffer] array to start writing at.
 *
 * Default: `0`
 *
 * @param be Whether the bytes should be written in Big Endian order.
 *
 * Default: `true`
 *
 * @return `8`, the number of bytes written to the buffer.
 */
fun ULong.getBytes(buffer: ByteArray, offset: Int = 0, be: Boolean = true): Int {
  if (offset + 8 >= buffer.size)
    throw Throwable("Writing the value $this to the given buffer would cause it to overflow!")
  unsafeGetBytes(buffer, offset, be)
  return 8
}

/**
 * Returns a [UByteArray] containing the 8 unsigned bytes that make up the
 * target `ULong` value.
 *
 * @param be Whether the bytes should be in Big Endian order.
 *
 * Default: `true`
 *
 * @return An array of 8 unsigned bytes.
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun ULong.getUBytes(be: Boolean = true): UByteArray {
  val out = UByteArray(8)
  unsafeGetBytes(out.asByteArray(), 0, be)
  return out
}

/**
 * Fills the given [buffer], starting from the given [offset], with the 8 bytes
 * that make up the target `ULong` value.
 *
 * If the given array's size is less than `offset + 8`, an exception will be
 * thrown.
 *
 * @param buffer `UByteArray` that will be filled with the bytes from the target
 * `ULong`.
 *
 * @param offset Offset position in the [buffer] array to start writing at.
 *
 * Default: `0`
 *
 * @param be Whether the bytes should be written in Big Endian order.
 *
 * Default: `true`
 *
 * @return `8`, the number of bytes written to the buffer.
 */
@OptIn(ExperimentalUnsignedTypes::class)
fun ULong.getUBytes(buffer: UByteArray, offset: Int = 0, be: Boolean = true) = getBytes(buffer.asByteArray(), offset, be)

private inline fun ULong.unsafeGetBytes(buffer: ByteArray, offset: Int, be: Boolean) {
  if (be) {
    buffer[offset    ] = ((this shr 56) and 0xFFu).toByte()
    buffer[offset + 1] = ((this shr 48) and 0xFFu).toByte()
    buffer[offset + 2] = ((this shr 40) and 0xFFu).toByte()
    buffer[offset + 3] = ((this shr 32) and 0xFFu).toByte()
    buffer[offset + 4] = ((this shr 24) and 0xFFu).toByte()
    buffer[offset + 5] = ((this shr 16) and 0xFFu).toByte()
    buffer[offset + 6] = ((this shr 8 ) and 0xFFu).toByte()
    buffer[offset + 7] = ((this       ) and 0xFFu).toByte()
  } else {
    buffer[offset + 7] = ((this shr 56) and 0xFFu).toByte()
    buffer[offset + 6] = ((this shr 48) and 0xFFu).toByte()
    buffer[offset + 5] = ((this shr 40) and 0xFFu).toByte()
    buffer[offset + 4] = ((this shr 32) and 0xFFu).toByte()
    buffer[offset + 3] = ((this shr 24) and 0xFFu).toByte()
    buffer[offset + 2] = ((this shr 16) and 0xFFu).toByte()
    buffer[offset + 1] = ((this shr 8 ) and 0xFFu).toByte()
    buffer[offset    ] = ((this       ) and 0xFFu).toByte()
  }
}