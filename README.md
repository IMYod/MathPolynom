# Polynom

In this project we created two main classes, which called "Polynom" and "Monom" that implements several interfaces. And also  a "Test" class for JUnit
We chose linkedlist as our data Structure, because we think it more useful, efficient and dynamic to changes. so that each node will contain one monom.

```
Note: We assumed that the input is normal , according to Elizabeth.
 For ex: 3*x^2+5        2*x^2-3 2x 2X
```

##  Monom

We needed to do a class which representing a function of the form f(x)= a*x^b. This class was very useful for us, because we used her methods to the polynom class.
So this class contains constructors and Methods.

### Constructors

1.	Get a two numbers one to the coefficient and one to the power, to create a new monom
2.	Copy from other monom.
3.	Makin from a string.

### Main Methods.

1.	derivative
This function compute a new monom which is the derivative of this monom.
2.	f 
This functiom compute the value of f(x).
3.	multiply
This function multiplies one monom in another monom.
4.	ToString
 This function print a string of the monom.


## Polynom

A polynomial consists of monoms, as we said earlier, his methods were helped by the Monom functions.

### Constructors

1.	Default
This is a default constructor for the polynom. We created a default monom and insert to the Polynom.
2.	Copy
This is a copy constructor from one polynom to another.
3.	String Transformer
This is a constructor that transforms a string to a polynom.


### Main Methods.

1.	Add – to add two polynoms.
2.	Copy – from one to another
3.	Derivate - Compute a new Polynom which is the derivative of this Polynom.
4.	Equals- Test if this Polynom is logically equals to another
5.	Multiply- Multiply two polynoms.
6.	Root
7.	Area


## Version

1.0

## Authors:

Yoav and Elad.

