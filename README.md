# CS342

Prog 1: Mid Meeting
Imagine you are planning a family reunion, and you want to find the midpoint to minimize the average distance* each person must travel to get there.  (You might have the same issue if you were planning a business meeting or a conference.)  Write the code to answer this question, given files with city distance information for a map as well as files for a list of people and their starting locations.
(*It would be more accurate to calculate the centroid, not the site with minimum average distance. Ah well.)


Map & Data Files

We will use the cities and distances taken from this map from an old atlas (see the larger 3MB version):
US Map with Distances

The 248 city names are stored in CityNames.txt, where the first few lines look like what is shown below.  The number on the first line (248) indicates how many (City, State) entries there are subsequently in the file, with one entry per line, with the city and state separated by a comma. 
248
Huntsville, AL
Birmingham, AL
Montgomery, AL
Mobile, AL
Grand Canyon, AZ
Page, AZ
Williams, AZ
Flagstaff, AZ
Phoenix, AZ
Tucson, AZ
Nogales, AZ
Ft Smith, AR
Little Rock, AR
Texarkana, AR
Lake Village, AR
Eureka, CA
. . .
The 711 entries of distances between adjacent cities are in file CityDistances.txt. Note that each entry is symmetric so that the file only has half the number of entries.  In other words between some city A and some city B the file has only the distance from A to B, and does not also have the distance from B to A.    The first few lines of that file look like what is shown below.  The number on the first line (711) indicates how many distance pairs there are subsequently in the file.  Each entry has two city numbers and the distance between them.  City number 1 corresponds to the first city in CityNames.txt (Huntsville), city number 2 corresponds to the second city in CityNames.txt (Birmingham), and so on.
[Thanks to CS 251 Fall 2014 student Joseph LeVeque for cleaning up these files as part of this assignment.]
711
1 2 100
1 102 119
1 194 131
1 195 102
2 3 91
2 4 258
2 50 146
2 53 268
2 102 178
2 103 117
2 104 145
2 193 237
2 195 147
3 4 170
3 50 160
3 52 335
3 53 213
3 103 192
3 104 156
3 39 213
4 39 243
. . .
The number of entries along with the id and city name should be in a file called Participants.txt.  The id should be a single string with no spaces, followed by a space and then the city name, which must match one of the cities in the CityNames.txt file described above.  One example of this file could be: 
17
Frank 58
Sterling 58
Estelle 58
Felicia 58
Justin 230
Marie 26
Joshua 197
Nancy 197
Roberto 197
Sofie 197
Anne 247
Matts 247
Emie 226
Dillhi 226
Erika 247
Stephan 247
Hannas 247
Note that for all of these files the contents could vary in terms of how many entries there are, but they must be consistent.  In other words the Participants.txt and CityDistances.txt files should only contain city numbers corresponding to cities actually in the CityDistances.txt file.
