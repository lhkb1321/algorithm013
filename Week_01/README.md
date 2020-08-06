# 算法训练营 第一周 总结

## 1）题型整理

## 类型分类

![分类](/Week_01/img/01.png)

## 数据结构

- ### 数组

  索引带给查询很大的优势，但是牺牲了其他性能。

  | add  | remove（not last） | update | search |
  | :--: | :----------------: | :----: | :----: |
  | O(n) |        O(n)        |  O(1)  |  O(1)  |

- ### 链表

  它的诞生，弥补了数组的不足，使用指针联系上下文(和数组互为补充) ，牺牲了查询性能。

  | add  | remove（not last） | update | search |
  | :--: | :----------------: | :----: | :----: |
  | O(1) |        O(1)        |  O(n)  |  O(n)  |

   ### = > 跳表

   链表对于查询性能优化的尝试，要提高查询性能，应该怎么办呢。答案还是建立索引(多级索引) 

   ![多级索引](/Week_01/img/02.jpg)

    建立像这样的多级索引,类似二分查找的思路，减少检索的次数
    跳表提升了查询性能，但是维护了多级的索引，牺牲了它的插入和删除性能(增加相应的索引)。
  |    add    | remove（not last） |  update   |  search   |
  | :-------: | :----------------: | :-------: | :-------: |
  | O(n*logn) |     O(n*logn)      | O(n*logn) | O(n*logn) |



- ### 栈和队列

    栈：先进后出

    | 常用方法  |                |
    | --------- | -------------- |
    | pop()     | 删除           |
    | isEmpty() | 空置判断       |
    | push()    | 添加           |
    | peek()    | 查看顶部的元素 |

    队列：先进先出

    | 常用方法 |                |
    | -------- | -------------- |
    | poll()   | 删除           |
    | empty()  | 空置判断       |
    | offer()  | 添加           |
    | peek()   | 查看顶部的元素 |

     栈 + 队列 的超级个体 : deque (同时具有栈和队列的特点)

    | **First Element (Head)** | **Last Element (Tail)**                                      |                                                              |                                                              |                                                              |
    | ------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
    |                          | *Throws exception*                                           | *Special value*                                              | *Throws exception*                                           | *Special value*                                              |
    | **Insert**               | [`addFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#addFirst-E-) | [`offerFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#offerFirst-E-) | [`addLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#addLast-E-) | [`offerLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#offerLast-E-) |
    | **Remove**               | [`removeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeFirst--) | [`pollFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pollFirst--) | [`removeLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeLast--) | [`pollLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pollLast--) |
    | **Examine**              | [`getFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getFirst--) | [`peekFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekFirst--) | [`getLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getLast--) | [`peekLast()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekLast--) |

    队列的特性

    |                       **Queue Method**                       |                 **Equivalent Deque Method**                  |
    | :----------------------------------------------------------: | :----------------------------------------------------------: |
    | [`add(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#add-E-) | [`addLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#addLast-E-) |
    | [`offer(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#offer-E-) | [`offerLast(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#offerLast-E-) |
    | [`remove()`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#remove--) | [`removeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeFirst--) |
    | [`poll()`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#poll--) | [`pollFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pollFirst--) |
    | [`element()`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#element--) | [`getFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#getFirst--) |
    | [`peek()`](https://docs.oracle.com/javase/8/docs/api/java/util/Queue.html#peek--) | [`peekFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peek--) |

    栈的特性

    |                       **Stack Method**                       |                 **Equivalent Deque Method**                  |
    | :----------------------------------------------------------: | :----------------------------------------------------------: |
    | [`push(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#push-E-) | [`addFirst(e)`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#addFirst-E-) |
    | [`pop()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#pop--) | [`removeFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#removeFirst--) |
    | [`peek()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peek--) | [`peekFirst()`](https://docs.oracle.com/javase/8/docs/api/java/util/Deque.html#peekFirst--) |

    实现

    ```java
    ## 以linkedList为列
    这里的双端队列,是使用两个双向链表实现的。这里的fist和last是维护的结构变化过程中头结点和尾结点的变化。每次操作
    都会同时维护这两个节点Node的变动。
    参考：https://juejin.im/post/6844903521431584782
        /**
         * Links e as first element.
         */
        private void linkFirst(E e) {
            // transient Node<E> first; 是一个双向链表
            final Node<E> f = first;
            final Node<E> newNode = new Node<>(null, e, f);
            first = newNode;
            if (f == null)
            // transient Node<E> last; 也是一个双向链表
                last = newNode;
            else
                f.prev = newNode;
            //长度维护    
            size++;
            modCount++;
        }

    ```



   
