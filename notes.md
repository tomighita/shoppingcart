#Notes
## Legacy Methods
- Since we have to deal with legacy content, I have decided to keep all methods signatures (since this is just a small part of a bigger system) and build my features arround that, while maintaining support for the legacy implementations
- These old methods were decorated with @Deprecated
## General Aspects
- First thing I consider to be quite important is to externalize the class for one product, for further abstractions and easy acces in other classes, as well as the possibility to further add attributes
- Since ShopProduct is a datamodel class, I have decided to keep the class attributes as private rather than setting up getters / setters
- Another thing to keep in mind is that primitive data types are not very precise; This is why I chose to store the price as a BigDecimal
- I have also added some converters to return the number of cents from BigDecimal price, and the reverse as well
- For items order: there is no default case for an example when we buy the same product multiple times, with some products in between. My approach was to still group all items with the same name but whenever we add the same item, we make it the latest. Therefore, if we buy apple, crisps, apple, we should return crisps and then apple.
- Tried to save timestamps on item additions, but realised it is not accurate, since CPU may reorder operations to improve performance, we can't gurantee the same order all the time on tests
- The ReceiptFormatter is assumed to be configurable by internal developers, therefore it is not failproof!
- I have tried avoiding Exception throwing, since I have seen we must return default values\
- I considered that other more complex testing suites (like mocking or e2e) weren't necesary in this context