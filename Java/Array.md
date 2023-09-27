_개인 공부내용을 기록한 것입니다. 학습은 공식 문서로!_

## Array

- 객체지향의 법칙을 따르는 대신 **'효율성'**에 집중하여 데이터를 저장한다. 
- 데이터를 '연속적인 영역의 메모리 주소'에 저장한다. 배열의 데이터가 실제 물리적으로 가깝게 저장되어있기 때문에 공간 지역성(Spatial Locality)이 높다. -> 따라서 인덱스로 데이터에 접근할 때 `O(1)` 의 시간이 걸린다.
- 배열의 메모리 주소는 첫 번째 데이터의 메모리 주소와 동일하다. 따라서 인덱스 접근을 할 때 `첫 번째 데이터의 메모리 주소 + (인덱스 * 각 데이터의 크기)` 결과값으로 데이터에 접근할 수 있다.



## ArrayList

- 객체지향의 법칙을 따르는데 데이터를 Array 형태로 저장한다.
- 데이터를 연속적인 메모리 주소로 저장해야하기 때문에 데이터 삽입, 삭제시 `O(N)`의 시간이 걸린다. 또한 배열은 크기가 정적으로 고정되어있으므로, 삽입시 새로운 배열을 생성하고 해당 배열에 기존의 데이터를 복사한다.
- [클래스 코드](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/ArrayList.java#L180)

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    @java.io.Serial
    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * 기본 크기
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 빈 배열일 경우
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * 데이터를 저장하는 배열!! Array로 저장한다는 증거!!
     * transient : 인스턴스 변수가 직렬화 과정에서 무시되어야한다는 뜻. 
     * elementData가 큰 데이터를 가리킬 수 있으며, 이를 직렬화 하는 것이 성능에 부정적일 수 있음.
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * ArrayList의 크기
     *
     * @serial
     */
    private int size;

    /**
     * 정해진 크기의 빈 배열을 만든다.(오버 로딩)
     *
     * @param  initialCapacity  용량
     * @throws IllegalArgumentException 용량이 음수인 경우
     */
    public ArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.elementData = new Object[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                                               initialCapacity);
        }
    }

    /**
     * 그냥 진짜로 빈 배열 만들기. (오버 로딩)
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * 데이터가 주어졌을 때 ArrayList인지 확인하고 elementData에 할당.
     *
     * @param c the collection whose elements are to be placed into this list
     * @throws NullPointerException if the specified collection is null
     */
    public ArrayList(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == ArrayList.class) {
                elementData = a;
            } else {
                // c가 LinkedList, HashSet등인 경우 ArrayList로 변환하여 저장
                elementData = Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            // replace with empty array.
            elementData = EMPTY_ELEMENTDATA;
        }
    }
    ...
}
```





## Linked List

- 각 데이터(노드)들은 메모리의 heap 영역의 임의의 위치에 저장될 수 있으며, 각 노드들은 (데이터, 다음 노드 포인터)를 저장한다.
- 특정 index의 노드를 찾기 위해선, 첫 번째 노드 부터 순차적으로 접근해야하므로 `O(N)`의 시간이 걸린다.
- 임의의 위치에 저장되므로 공간 지역성도 낮다 -> 캐시에 로드될 때 연속된 데이터가 로드 X -> 배열에 비해 캐시 효율성이 떨어짐.
- 대신 메모리 주소가 연속적일 필요가 없어 데이터 삽입, 삭제시 포인터만 변경해주면 되어서 `O(1)`의 시간이 걸린다.
- 동적으로 크기를 조정할 수 있다. 

- [클래스 코드](https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/util/LinkedList.java#L128)

```java
public class LinkedList<E>
    extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable, java.io.Serializable
{
    transient int size = 0;

    /**
     * 첫 번째 노드 포인터
     */
    transient Node<E> first;

    /**
     * 마지막 노드 포인터
     */
    transient Node<E> last;

    /**
     * 생성자
     */
    public LinkedList() {
    }

    /**
     * Constructs a list containing the elements of the specified
     * collection, in the order they are returned by the collection's
     * iterator.
     *
     * @param  c 입력받은 데이터
     * @throws NullPointerException if the specified collection is null
     */
    public LinkedList(Collection<? extends E> c) {
        this();
        addAll(c);
    }
    // 중간 생략
    
    /**
     * 입력받은 데이터를 특정 위치에서부터 지금의 LinkedList에 추가한다.
     *
     * @param index c의 어느 위치에서부터 저장할 것인지
     * @param c 이 LinkedList에 저장될 입력받은 데이터 Collection
     * @return {@code true} if this list changed as a result of the call
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     */
    public boolean addAll(int index, Collection<? extends E> c) {
        // index가 실제로 존재할 수 있거나 add할 수 있는 위치인지 확인한다.
        checkPositionIndex(index);
        
		// 입력받은 collection을 Array로 변경
        Object[] a = c.toArray();
        // 입력받은 collection의 크기
        int numNew = a.length;
        if (numNew == 0)
            return false;

        Node<E> pred, succ;
        if (index == size) {
            // 마지막 위치부터 추가
            succ = null;
            pred = last;
        } else {
            // 원하는 위치에 있는 노드 찾음
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : a) {
            @SuppressWarnings("unchecked") E e = (E) o;
            // 노드의 앞, 데이터, 뒤를 입력하여 데이터 생성
            Node<E> newNode = new Node<>(pred, e, null);
            // 앞이 비어있으면, 헤드가 된다.
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            // 마지막이 비어있다면 -> 기존의 마지막 위치부터 추가했음
            // 가장 마지막 Node로 설정
            last = pred;
        } else {
            // 마지막이 이미 노드가 있었다면 -> 해당 노드를 지금 추가한 노드들의 꼬리에 추가
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        modCount++;
        return true;
    }
    
    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    private void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    
    /**
     * 입력받은 index가 실제로 존재하는 위치인지 본다.
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }
    
    /**
     * 입력받은 index가 해당 iterator에서 유효한 위치거나, add 할 수 있는 위치인지 본다.
     */
    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }
    
        
    /**
     * 첫 번째 노드를 반환한다. 첫 번째는 미리 저장해두기 때문에 아주 빠르게 찾을 수 있다. 
     * 마지막도 마찬가지
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getFirst() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }
    
    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }
    
    /**
     * Linked List에서 index 기반으로 노드를 찾는데  O(N)이 걸리는 이유
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);
        
		// index가 연결 리스트의 중간 인덱스보다 작은지 여부 조사
        if (index < (size >> 1)) {
            // 앞에서 가까우므로 앞에서부터 탐색
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            // 뒤에서 가까우므로 뒤에서부터 탐색
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
}
```

