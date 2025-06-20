package ua.com.numberfacts.domain

import java.math.BigInteger

data class NumberFact(
    val number: BigInteger = BigInteger.ZERO,
    val description: String = ""
)
