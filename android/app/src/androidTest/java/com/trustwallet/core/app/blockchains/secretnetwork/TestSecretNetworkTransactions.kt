package com.trustwalval.core.app.blockchains.secretnetwork

import android.util.Log
import com.google.protobuf.ByteString
import com.trustwallet.core.app.utils.toHexByteArray
import org.junit.Assert.assertEquals
import org.junit.Test
import wallet.core.java.AnySigner
import wallet.core.jni.*
import wallet.core.jni.CoinType.SECRETNETWORK
import wallet.core.jni.proto.Cosmos
import wallet.core.jni.proto.Cosmos.SigningOutput

class TestSecretNetworkTransactions {

    init {
        System.loadLibrary("TrustWalletCore")
    }

    @Test
    fun testSigningTransaction() {
        val key =
            PrivateKey("0bd80fcf10da8a58d50ebaabdcccc07a51d698a157dcac53a6f78bd57e0820f5".toHexByteArray())
        val publicKey = key.getPublicKeySecp256k1(true)
        val from = AnyAddress(publicKey, SECRETNETWORK).description()

        val txAmount = Cosmos.Amount.newBuilder().apply {
            amount = 1000000
            denom = "uscrt"
        }.build()

        val sendCoinsMsg = Cosmos.Message.Send.newBuilder().apply {
            fromAddress = from
            toAddress = "enigma1wq22m5g0khkaehuatu242eh2lx86ulqnftp4zc"
            addAllAmounts(listOf(txAmount))
        }.build()

        val message = Cosmos.Message.newBuilder().apply {
            sendCoinsMessage = sendCoinsMsg
        }.build()

        val feeAmount = Cosmos.Amount.newBuilder().apply {
            amount = 3000
            denom = "uscrt"
        }.build()

        val cosmosFee = Cosmos.Fee.newBuilder().apply {
            gas = 200000
            addAllAmounts(listOf(feeAmount))
        }.build()

        val signingInput = Cosmos.SigningInput.newBuilder().apply {
            accountNumber = 158
            chainId = "enigma-1"
            memo = ""
            sequence = 0
            fee = cosmosFee
            privateKey = ByteString.copyFrom(key.data())
            addAllMessages(listOf(message))
        }.build()

        val output = AnySigner.sign(signingInput, SECRETNETWORK, SigningOutput.parser())
        val jsonPayload = output.json

        val expectedJsonPayload = """{"mode":"block","tx":{"fee":{"amount":[{"amount":"3000","denom":"uluna"}],"gas":"200000"},"memo":"","msg":[{"type":"bank/MsgSend","value":{"amount":[{"amount":"1000000","denom":"uluna"}],"from_address":"enigma13w5muy6ff7669qkpjkvru24y6kf7ljme8ty9u0","to_address":"enigma1wq22m5g0khkaehuatu242eh2lx86ulqnftp4zc"}}],"signatures":[{"pub_key":{"type":"tendermint/PubKeySecp256k1","value":"A13xhVZlIdangCMZ7gbhoo6Xt3ct+1/dE8pvBXVRiWjk"},"signature":"KPdiVsKpY12JG/VKEJVa/FpMKclxlS0qNNG6VOAypj10R5vY5UX5IgRJET1zNYnH0wvcXxfNXV+s8jtwN2UXiQ=="}]}}"""
        assertEquals(expectedJsonPayload, jsonPayload)

    }
}
