package com.example.tokenAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.example.tokenAPI.controller","com.example.tokenAPI.service"})
public class TokenApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokenApiApplication.class, args);



		/*Blockchain connection*//*
		System.out.println("Connecting to Ethereum ...");
		Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));
		System.out.println("Successfuly connected to Ethereum");

		// Prepare a wallet
		try {
			File file = ResourceUtils.getFile("classpath:pk.txt");
			Scanner myReader = new Scanner(file);
			//while (myReader.hasNextLine()) {
			String pk = myReader.nextLine();
			System.out.println(pk);
			//}
			myReader.close();

			//String pk = "0x1356db8ade79eb01813509007b7758f95c727da88aea29aaacd07e833a158a20";
			Credentials credentials = Credentials.create(pk);

			// Load the contract
			String contractAddress = "0x89D6300FF07c06Ab3c47B360478A78e67ADf91A9";
			ERC20 ERC20Token = ERC20.load(contractAddress, web3, credentials, new DefaultGasProvider());

			try {

				// web3_clientVersion returns the current client version.
				Web3ClientVersion clientVersion = web3.web3ClientVersion().send();

				// eth_blockNumber returns the number of most recent block.
				EthBlockNumber blockNumber = web3.ethBlockNumber().send();

				// eth_gasPrice, returns the current price per gas in wei.
				EthGasPrice gasPrice = web3.ethGasPrice().send();

				// eth_accounts
				EthAccounts accounts = web3.ethAccounts().send();

				// eth_getTransactions
				EthGetTransactionCount getTransactions = web3.ethGetTransactionCount("0x0EE6F7d5005647e0F2Fbddb8B600f15fB9A28632", DefaultBlockParameter.valueOf("latest")).send();

				//Token information
				String symbol = ERC20Token.symbol().send();
				String name = ERC20Token.name().send();
				BigInteger decimal = ERC20Token.decimals().send();
				BigInteger balance1 = ERC20Token.balanceOf("0x0EE6F7d5005647e0F2Fbddb8B600f15fB9A28632").send();
				BigInteger balance2 = ERC20Token.balanceOf("0x42FC7db832A3C8A74eb33682a41C6bCa4583f9b9").send();


				// Print result
				System.out.println("Client version: " + clientVersion.getWeb3ClientVersion());
				System.out.println("Block number: " + blockNumber.getBlockNumber());
				System.out.println("Gas price: " + gasPrice.getGasPrice());
				System.out.println("Accounts: " + accounts.getAccounts());
				System.out.println("Transactions: " + getTransactions.getTransactionCount());

				System.out.println("symbol: " + symbol);
				System.out.println("name: " + name);
				System.out.println("decimal: " + decimal.intValueExact());
				System.out.println("Account[0] balancea: " + balance1.intValueExact());
				System.out.println("Account[1] balancea: " + balance2.intValueExact());

				TransactionReceipt receipt = ERC20Token.transfer("0x42FC7db832A3C8A74eb33682a41C6bCa4583f9b9", new BigInteger("5")).send();
				//System.out.println("Transaction hash: "+receipt.getTransactionHash());
				//System.out.println("Account[0] balanceb: " + balance1.intValueExact());
				//System.out.println("Account[1] balanceb: " + ERC20Token.balanceOf("0x42FC7db832A3C8A74eb33682a41C6bCa4583f9b9").send());
				List<ERC20.TransferEventResponse> events = ERC20Token.getTransferEvents(receipt);
				events.forEach(event
						-> System.out.println("from: " + event._from + ", to: " + event._to + ", value: " + event._value));

			} catch (Exception ex) {
				throw new RuntimeException("Error whilst sending json-rpc requests", ex);
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred when private key is read.");
			e.printStackTrace();
		}
*/
	}


}
