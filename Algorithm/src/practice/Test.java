package practice;

import java.util.HashSet;

public class Test {
	
	static class Person {
		String name;
		int age;
		
		public Person(String name, int age) {
			this.name = name;
			this.age = age;
		}
		
		@Override
		public boolean equals (Object o) {
			if (this == o)
				return true;
			if (o == null)
				return false;
			if (getClass() != o.getClass())
				return false;
			final Person p = (Person) o;
			
			if (name == null) {
				if (p.name != null)
					return false;
			} else if (!name.equals(p.name))
				return false;
			return true;
						
		}
		
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//	        int result = 1;
//	        result = prime * result
//	                + ((name == null) ? 0 : name.hashCode());
//	        return result;
//		}
	}

	public static void main(String[] args) {
		HashSet<Person> set = new HashSet<>();
		Person me = new Person("하은", 28);
		Person you = new Person("하은", 28);
		
		set.add(me);
		set.add(you);
		
		System.out.println(me.equals(you));
		System.out.println(set);
		

	}

}
