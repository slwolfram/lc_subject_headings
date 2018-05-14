this directory contains csv versions of the heading structure. 

subjectheadings.csv gives all the subject heading names (sh_name), uris (sh_uri), and the number of catalog items assigned each heading (sh_count)

broaderheadings, narrowerheadings, relatedheadings, and subdivisions are all structured the same: the first column (e.g. bh_sh_uri for broaderheadings)
gives the heading uri to which the adjacent heading is assigned; the second column (e.g. bh_uri) gives the adjacent heading uri. So the heading at bh_sh_uri
has the broader heading at bh_uri. For some reason I included the subdivision names in subdivisions.csv and didn't include the names for any of the other
adjacent heading csv's, since only the links matter - all of the heading names can be looked up in subjectheadings.csv.

I also included the catalog record data I was able to extract (itemrecords.csv) and item_subjectheadings, which gives the subjectheading assignments for 
item records. 
