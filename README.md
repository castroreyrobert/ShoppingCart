# ShoppingCart

# Objectives:
1. Build a proof-of-concept shopping cart, wherby a user can add products to their cart and process the order through a checkout form
2. Fetch the product items from the provided products.json file to display on the Product Listing view based on the design
3. The cart contents should update in real-time when a user adds a product to the cart
4. Cart contents should persist throughout the users session, including on refresh, until the user clears their contents or places an order
5. Each submitted order should have a unique identifier and there should be checks in place to determine if it is unique
6. Upon checkout, the order data should be saved locally to a .json file labelled with the unique order ID e.g order_2948281.json

 ## Requirements:
  - Android version 24(Lollipop) - Latest
  
   ## Additional Information:
   1. MVVM Architecture - (Prepared a viewmodel for the activity but not currently used in this repo)
   2. Language - Kotlin
   3. Libraries:
      - Dagger for Dependency Injection
      - Android Jetpack Library
      - CircleImageView
