# OnlineStoreAPI
Basic Online Store API

Getting Started

  1. Clone this project or download.
  2. Run  **'mvn clean package'** to build artifacts.
  3. To run the application: java -jar target/smart_hardware_shop-0.0.1-SNAPSHOT.jar
  4. Run docker container 'docker-compose -f docker-compose.yml up'
  
  
Following data will be pre-loaded once you run the application.
### Customers
| ID          | Name        |
| ----------- |:-------------:|
| 1      	  | Noman	  
| 2     	  | Alex     

### Products

| ID | Name| Description | Price
| -----------  |:-------------:|:-------------:|:-------------:|
|1	| Jig-saw     | This 20V MAX Jig Saw has an all metal, lever-action keyless blade..	 | 80.99
|2	| Leaf-Blower | The EGO POWER+ string trimmer and blower combo kit ..	    	     |  109.90  |
|3	| pip-cutter  | This 120V MAX pip cutter has an all metal, lever-action keyless blade..	 |  180.99  |
|4	| screwdriver | The POWER+ string trimmer and blower combo kit are the perfect pair..	  |  209.90  |
|5	| screwdriver | The POWER++ string trimmer and blower combo kit are the perfect pair .... |  509.90  |



#### API List product
### GET http://localhost:8080/api/v1/products/
#### Headers
```json
[{"key":"Authorization","value":"bearer {admin_token}"}]
```
#### Response 200 OK
----

```json
[
  {
    "id": 1,
    "name": "Jig-saw",
    "price": 80.99,
    "description": "This 20V MAX Jig Saw has an all metal, lever-action keyless blade clamp designed for quick and easy blade changes.."
  },
  {
    "id": 2,
    "name": "Leaf-Blower",
    "price": 109.9,
    "description": "The EGO POWER+ string trimmer and blower combo kit are the perfect pair to get all your yard maintenance completed"
  },
  {
    "id": 3,
    "name": "pip-cutter",
    "price": 180.99,
    "description": "This 120V MAX pip cutter has an all metal, lever-action keyless blade clamp designed for quick and easy blade changes.."
  },
  {
    "id": 4,
    "name": "screwdriver",
    "price": 209.9,
    "description": "The POWER+ string trimmer and blower combo kit are the perfect pair to get all your yard maintenance completed"
  },
  {
    "id": 5,
    "name": "screwdriver",
    "price": 509.9,
    "description": "The POWER++ string trimmer and blower combo kit are the perfect pair to get all your yard maintenance completed"
  }
]
```

#### Add product
### POST http://localhost:8080/api/v1/products/create

#### Headers
```json
[{"key":"Authorization","value":"bearer {admin_token}"}]
```

#### Request
``` json
{

	"name" : "New product",
	"price": 78.00,
	"description" : "this is new product.. "
}

```
#### Response 201 Created
``` json

    "id": 8,
    "name": "new-product",
    "description": "this is new product",
    "price": 18.99
```
#### Show complete orders from a User
### GET http://localhost:8080/api/v1/orders/user/1
```json
[{"key":"Authorization","value":"bearer {customer_token}"}]
```
#### Response 200 OK
``` json
[
    {
        "userId": 1,
        "name": "Noman",
        "products": [
            {
                "id": 1,
                "name": "Jig-saw",
                "price": 80.99,
                "description": "This 20V MAX Jig Saw has an all metal, lever-action keyless blade clamp designed for quick and easy blade changes.."
            },
            {
                "id": 2,
                "name": "Leaf-Blower",
                "price": 109.9,
                "description": "The EGO POWER+ string trimmer and blower combo kit are the perfect pair to get all your yard maintenance completed"
            }
        ],
        "orderDate": "2021-09-28T23:00:00.000+00:00"
    },
    {
        "userId": 1,
        "name": "Noman",
        "products": [
            {
                "id": 3,
                "name": "pip-cutter",
                "price": 180.99,
                "description": "This 120V MAX pip cutter has an all metal, lever-action keyless blade clamp designed for quick and easy blade changes.."
            },
            {
                "id": 4,
                "name": "screwdriver",
                "price": 209.9,
                "description": "The POWER+ string trimmer and blower combo kit are the perfect pair to get all your yard maintenance completed"
            }
        ],
        "orderDate": "2021-09-27T22:00:00.000+00:00"
    }
]
```
#### Place order
User 1 placing an order with product 3 and  1"
### POST http://localhost:8080/api/v1/orders

#### Headers
```json
[{"key":"Authorization","value":"bearer {customer_token}"}]
```
#### Request
``` json
{
	"userId" : 1,
	"products" : [
		{ 
			"id" : 3,
			"price" : 78.00
		},
		{ 
			"id" : 1,
			"price" : 1200.00
		}	
	],
	"localDate": "2021-09-29"
}
```
#### Response 201 Created
``` json
{
    "userId": 1,
    "name": "Noman",
    "products": [
        {
            "id": 3,
            "name": "pip-cutter",
            "price": 180.99,
            "description": "This 120V MAX pip cutter has an all metal, lever-action keyless blade clamp designed for quick and easy blade changes.."
        },
        {
            "id": 1,
            "name": "Jig-saw",
            "price": 80.99,
            "description": "This 20V MAX Jig Saw has an all metal, lever-action keyless blade clamp designed for quick and easy blade changes.."
        }
    ],
    "orderDate": null
}
```
 
