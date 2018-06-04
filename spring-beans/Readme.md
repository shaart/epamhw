## Task 3 (10 points)
Create Spring console app. Use XML as configuration format.
1. Create bean `A`, use DI via setters, use property placeholder for values
2. Create bean `B`, use DI via constructor (bean `A` as argument of constructor)
3. Create bean `C` with `singleton` scope and bean `D` with `prototype` scope. We need to add
bean `D` as property of bean `C`. Pay attention that they have different scopes. Consider
Lookup Method Injection.
4. Create bean `E` and replace logic of one of his method by Method Replacement
5. Create bean `F` and log all possible steps from his lifecycle (lifecycle of Spring bean).