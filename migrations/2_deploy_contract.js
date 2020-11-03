const MyToken = artifacts.require("./MyToken.sol");

module.exports = async function(deployer, network, accounts) {
 // Deploy the smart contract
    const [admin, receptor, _] = accounts;

    await deployer.deploy(MyToken);
    const tkn = await MyToken.deployed();
    await tkn.tokenizer(admin, web3.utils.toWei('10000'));
    // 1 tkn = 1 * 10 ^ 18 wei

 // deployer.deploy(ERC20Token, {from: accounts[0]}).then(function(instance) {

};
