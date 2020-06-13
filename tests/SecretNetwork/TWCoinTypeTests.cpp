// Copyright © 2017-2020 Trust Wallet.
//
// This file is part of Trust. The full Trust copyright notice, including
// terms governing use, modification, and redistribution, is contained in the
// file LICENSE at the root of the source code distribution tree.
//
// This is a GENERATED FILE, changes made here MAY BE LOST.
// Generated one-time (codegen/bin/cointests)
//

#include "../interface/TWTestUtilities.h"
#include <TrustWalletCore/TWCoinTypeConfiguration.h>
#include <gtest/gtest.h>


TEST(TWSecretNetworkCoinType, TWCoinType) {
    auto symbol = WRAPS(TWCoinTypeConfigurationGetSymbol(TWCoinTypeSecretNetwork));
    auto txId = TWStringCreateWithUTF8Bytes("443F0FDEF490F4C297C16A73C677DB5C0BF01A2EB67A14639CFF001EE0EEDFC7");
    auto txUrl = WRAPS(TWCoinTypeConfigurationGetTransactionURL(TWCoinTypeSecretNetwork, txId));
    auto accId = TWStringCreateWithUTF8Bytes("enigma1qx5pppsfrqwlnmxj7prpx8rysxm2u5vz4hlwlr");
    auto accUrl = WRAPS(TWCoinTypeConfigurationGetAccountURL(TWCoinTypeSecretNetwork, accId));
    auto id = WRAPS(TWCoinTypeConfigurationGetID(TWCoinTypeSecretNetwork));
    auto name = WRAPS(TWCoinTypeConfigurationGetName(TWCoinTypeSecretNetwork));

    ASSERT_EQ(TWCoinTypeConfigurationGetDecimals(TWCoinTypeSecretNetwork), 6);
    ASSERT_EQ(TWBlockchainCosmos, TWCoinTypeBlockchain(TWCoinTypeSecretNetwork));
    ASSERT_EQ(0x0, TWCoinTypeP2shPrefix(TWCoinTypeSecretNetwork));
    ASSERT_EQ(0x0, TWCoinTypeStaticPrefix(TWCoinTypeSecretNetwork));
    assertStringsEqual(symbol, "SCRT");
    assertStringsEqual(txUrl, "https://explorer.cashmaney.com/transactions/443F0FDEF490F4C297C16A73C677DB5C0BF01A2EB67A14639CFF001EE0EEDFC7");
    assertStringsEqual(accUrl, "https://explorer.cashmaney.com/account/enigma1qx5pppsfrqwlnmxj7prpx8rysxm2u5vz4hlwlr");
    assertStringsEqual(id, "secretnetwork");
    assertStringsEqual(name, "SecretNetwork");
}
