##UPDATE 2015-11-8

On the eve of the deadline, I think we can finally declare Part 1 complete, tested for weird inputs and everything. We're going to use **Laura's code**, that is, all three classes inside the directory called “Laura's part”: ``BookInventory1.java``, ``Book.java``, and ``DuplicateIsbnException.class``.

##UPDATE 2015-10-25

The big problem was getting to read the input file the prof sent line by line, as each line contained all the attributes required to initialise a single book object. Thanks to Laura, we found the ``BufferedReader`` class, which has a method that does exactly that. So once we were able to parse the file line by line, the next challenge was to split each line into different tokens that could then be used as parameters for the Book constructor. Using the method ``split()`` (which takes a regular expression as parameter, more on that later) we break down each line using whitespace as the delimiter between each token. Since each piece of the broken down String is still a String, we then used each type's parse method to convert each token into the appropriate type, eg: if we have the String fragment “829481923”, then ``Long.parseLong("829481923")`` will return a ``long`` variable of that same value. With this, we could easily populate BkArray with all the Book objects.

In case you're curious about regular expressions, they are “a sequence of characters that define a search pattern, mainly for use in pattern matching with strings, or string matching, i.e. "find and replace"-like operations,” according to the Wikipedia article. The one I used was “\\s+” which looks for a blank space, denoted by “\s”, that is followed by any other character, denoted by “+”. If a match is found, the delimiter is placed there and ``split()`` returns the String segment. 
