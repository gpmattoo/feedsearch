Feedsearch
==========

Application which indexes and searches syndication feeds to send alerts when certain terms appear.

background
----------

I built this application to test some theories.  I wanted to see how easy it would be to create a notification 
system for Craigslist.  I decided to take the following approach:

* Allow users to create alerts by specifying their email address and search terms
* Periodically pull down an RSS feed and index it using Lucene
* Search all alert terms against the indexed syndication feed and send alerts for matches

Alerts could be extended to SMS or any kind of notification mechanism.  It would also be an interesting
experiment to allow users to specify their location and limit search matches based on geographic distance.

Please note that this application would violate Craigslist's terms of use if deployed as described above so 
I don't think it will ever move beyond a hobby project.  It would be possible to use it for indexing/alerting
on other syndication feeds but I think companies like Google already do a good enough job of this.

