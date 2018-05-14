This repository contains a set of tools for working with LC Subject Headings in Java.

The heading structure has been translated from the LC Linked Data Library for Subject Headings,
found here: https://id.loc.gov/download/.

To see the way the headings are structured, check out the LCSH class in lc_analyze/src/main/java. 
Each heading has a name, uri, variant terms, and several fields which relate it to other headings.

The heading data is stored in a bunch of csv files in the lcsh_csvs folder. Using "loadCSV" program, 
you can load the data from the csv's into a java model of the full heading structure and take things
from there.  

Loading from the csv's is pretty time consuming, though, so I have also included a serialization of the heading structure
which can be loaded much more quickly (see loadCSV). 

