# VANET implementation using Hashmap

VANET, which stands for Vehicular ad hoc network, is a subset of mobile ad hoc networks. It is composed of vehicles and roadside units (RSU) as network nodes and enables cars equipped with communication technology to perform inter-vehicular communication (V2V) as well as roadside-to-vehicle communication (RVC). Communication can also happen between Vehicles and Pedestrians (V2P).

The vehicle structure is represented as a graph in VANET. Each vehicle is represented in the graph by a vertex, while communication with another vehicle is represented by an edge. Because VANETs are typically utilized in emergency situations, the message has to arrive at its destination with as little delay as possible. However, due to the short range of the VANET connection (300-400m), the message is routed by forwarding it from one vehicle to another until it reaches the vehicles within the range. To determine the next forwarders in this type of routing, each vehicle must execute some computations on the graph topology. These computations must be based on rapid algorithms with minimal computation time complexity in order to achieve efficient and time-essential communication.

We have implemented the VANET as a Hashmap of Hashmaps. The key for each vehicle represents a vertex, and the corresponding value is yet another hashmap which contains the neighbouring vertex, along with their corresponding edge weights. Since VANETs deal with real-time data, vehicles may be added and removed from the network continuously. This generates a lot of insertions and deletions to the data structure being used. Hashmaps have insertions and deletions in constant time as compared to lists that have O(n) lookup times.


We have implemented both LinkedListHashMap and TreeHashMap to compare their differences. The TreeHashMap has shown significantly improved performances for lookup, insertion, and deletion operations in case of a collision since BST has a time complexity O(log n) when compared to a linked list that has a time complexity of O(n). 

As the number of edges in the graph increases, the Hash Map implementation exhibits a significant advantage over the Adjacency List implementation in terms of the time taken for adding edges. The Binary Search Tree implementation performed well in handling collisions as it takes O(log n) time to insert an element when a collision has occurred. 


### Contributors:

Vivek Murarka (22200673)

Nikhitha Grace Josh (22200726)

Purvish Shah (22200112)

Ravi Raj Pedada (22200547)

Meghana Kamsetty Ravikumar (22200568)
