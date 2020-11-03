pragma solidity ^0.6.2;

import "@openzeppelin/contracts/token/ERC20/ERC20.sol";

contract MyToken is ERC20 {
    constructor() public ERC20("MyToken", "MYT") {
        //_mint(msg.sender, 1000);
    }

    function tokenizer(address to, uint256 amount) external {
        _mint(to, amount);
    }

}