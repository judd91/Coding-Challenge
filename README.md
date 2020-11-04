# Tokenizer API

The project consist on the development of a REST Api to make payments by creating a ERC20 token.
### Structure

The project is divided in two parts, Blockchain and Token API.

From Token API, users could make payments transfering tokens giving the private key, their own account, the destination account and the amount. All tranfer events would be stored in a database.

It is necessary to have the local blockchain and database server running.


## Getting Started
There are described the instructions to setting up the project locally. 

### Requirements
The Token Application is implemented in Java 8. MyToken.sol smart contract is implemented by using the [Zeppeling]() library which provides the implementation of the ERC20 standard in Solidity 0.6.2 .

* Java 8
* npm

A list of frameworks used in the API development.
* [Spring-Boot](https://spring.io/projects/spring-boot)
* [Maven](https://maven.apache.org/)
* [MySQL](https://www.mysql.com/)

Necesary tools to deploy the local blockchain.
* [Truffle](https://www.trufflesuite.com/truffle) (ganache-cli is included)
* [Node JS](https://nodejs.org/es/download/releases/). It is necessary to have the 12.x version to launch Ganache-Cli.

There are multiple possible tools to execute REST services. For this project was used :
* [Postman](https://www.postman.com/) 

### Installation

#### Blockchain environment
After install truffle, open a terminal and execute the following command to deploy the local blockchain:

```sh
npx ganache-cli
```
Inside _tuffle-config.js_ file define the Solidity version for the compiler:

```
compilers: {
    solc: {
      version: "0.6.2"
      }
}
```
and the network where the blockchain will run.

```
  networks: {
    development: {
	  host: "127.0.0.1",     // Localhost (default: none)
      port: 8545,            // Standard Ethereum port (default: none)
      network_id: "*",       // Any network (default: none)
     }
}
```
It would be necessary to deploy a contract in the network to simulate the proper environment before use the API.
```
truffle migrate --network development
```




#### Token API
In order to deploy the application, after install Maven, execute:

```sh
mvn spring-boot:run
```
All necessary dependencies are added in the POM file.

The address of the deployed smart contract needs to be added into the TokenApi.java service file to the variable contractAddress.

## Usage

After create the environment with the blockchain, database service and TokenApi running, it is time to try the application.

It is possible to:
* Make a transfer

```
# http://localhost:your-port/payment

{
    "amount" : 1,
    "accountfrom" : "wallet-address",
    "accountto" : "wallet-address-to",
    "pk": "private-key"
}
```
* Get Events by paiment ID. Paiment ID is assigned to every operation.
```
http://localhost:8080/payment/get?paymentId={add-payment-id}
```

* Get All Events
```
http://localhost:8080/payment/all

```