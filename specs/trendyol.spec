Trendyol Test SG Veteris
==========================

This is an executable specification file. This file follows markdown syntax. Every heading in this file denotes a scenario. Every bulleted point denotes a step.
To execute this specification, use `mvn test`



Control of the main page opening
-----------
Tags:openMainPage
* Open Trendyol Home Page and control of the main page


Login check with incorrect information
-----------
Tags:checkWithIncorrect
* Open Trendyol Home Page and control of the main page
* Click on the Login button on the homepage
* Typing incorrect "test@test.com" in the email field
* Typing incorrect "123456" in the password field
* Click on the login button

Control of “laptop” search
-----------
Tags:controlLaptopSearch
* Open Trendyol Home Page and control of the main page
* Click on the search bar and type "huawei laptop"
* Verify "huawei laptop" is displayed search page

Control of Random laptop selection
-----------
Tags:controlRandomLaptopSelection
* Open Trendyol Home Page and control of the main page
* Click on the search bar and type "huawei laptop"
* Select a random product
* Check product detail page is opened

Control of Adding the selected laptop to the cart
-----------
Tags:controlAddCart
* Open Trendyol Home Page and control of the main page
* Click on the search bar and type "huawei laptop"
* Select a random product
* Click on the add to cart button
* Click on the my basket button on the page

Product price control before and after adding to the cart
-----------
Tags:priceControl
* Open Trendyol Home Page and control of the main page
* Click on the search bar and type "huawei laptop"
* Select a random product
* Click on the add to cart button
* Click on the my basket button on the page
* Verifying that prices remain the same

Control of increase the number of product in the basket to two
--------
Tags:controlIncreaseProductAmount
* Open Trendyol Home Page and control of the main page
* Click on the search bar and type "kitap"
* Select a random product
* Click on the add to cart button
* Click on the my basket button on the page
* Click on the product plus button on the basket page
* Verify product price and quantity increment

Control of deleting all products in the basket
---------
Tags:controlDeletingAllProductInBasket
* Open Trendyol Home Page and control of the main page
* Click on the search bar and type "kitap"
* Select a random product
* Click on the add to cart button
* Click on the my basket button on the page
* Click on the product plus button on the basket page
* Click on the bin button next to the product on the basket