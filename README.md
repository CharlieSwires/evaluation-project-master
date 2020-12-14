
DAI Java Evaluation Exercise
============================

Welcome to the DAI Java Evaluation Exercise.

This is a Java project for a very simple REST service, which is supposed to calculate the total volume of boxes.
 The HTTP client POSTs a JSON object with the dimensions of the boxes, and the service responds with the
 total volume. The service ignores units, for simplicity.
 
So for example an input of two boxes, one with dimensions 2x3x4 and one with dimensions 1x1x1, would have a total 
volume of 25.
 
However, the service logic is missing, and the tests are incomplete.

The OpenAPI Definition
----------------------

Much of the code of this project has been generated from an OpenAPI definition, from the included
 evaluation-project-1.0.0.yaml file. If you're not familiar with OpenAPI, you can find information about it
 here: https://swagger.io/docs/specification/about/

You can probably get the service to calculate volume totals without thinking about this file. But, it does contain
 an example. You'll also need to look at it to write accurate tests. Especially, because the file defines the format of
 error messages.

The Task
--------

There are TODO comments throughout the project indicating what is missing. You will find them in:

* VolumeCalculatorImpl.java
* ExcerciseApplicationTests.java
* evaluation-project-1.0.0.yaml

Attempt as many of the TODOs as you can. Two of the TODOs are marked as "advanced". We don't
necessarily expect candidates of all levels to be able to complete all the TODO tasks. Do what you can in the time
available.

You are free to add more tests, as well as to add new classes, as you see fit to complete the TODOs.

Submitting Your Solution
------------------------

You will have received this project by email. The email will provide the deadline.
Please submit your zipped solution in an attachment, in response to that email.# evaluation-project-master
