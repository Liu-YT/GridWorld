* 文件夹---`1`

  使用`SparseGridNode`实现`SparseBoundedGrid`

* 文件夹---`2`

  使用`HashMap`实现`SparseBoundedGrid`

* 文件夹---`3``

  `UnBoundedGrid`的实现

`r = the num of row of the grid `

`c = the num of col of the grid `

`n = the num of actor in the grid `

| 方法                           | `SparseGridNode`版 | `LinkedList<OccupantInCol>`版 | `HashMap`版 | `TreeMap`版 |
| :----------------------------- | :----------------: | :---------------------------: | :---------: | :---------: |
| `getNeighbors`                 |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `getEmptyAdjacentLocations`    |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `getOccupiedAdjacentLocations` |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `getOccupiedLocations`         |       O(c+n)       |            O(r+n)             |    O(n)     |    O(n)     |
| `get`                          |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `put`                          |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `remove`                       |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |

* get方法的Big-Oh效率是`O(1)`
* 行列索引值在当前数组范围内时，put方法的效率是
  * O(n2)， n是数组size

