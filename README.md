# CS301 Personal Guide — Chapter 3: Object-Oriented Programming (Princeton IntroCS)

Author: Wesley Weaver  
Date: October 19, 2025

> Source hub: https://introcs.cs.princeton.edu/java/30oop/

## Why this chapter matters
Object-oriented programming lets you define *data types* that bundle state and behavior. In practice this means tighter APIs, fewer accidental couplings, and code you can reason about. This chapter moves from *using* library types to *creating* your own, then *designing* them well, and finally a full case study (N-Body).

---

## 3.1 Using Data Types
**Concepts:** reference types, constructors, instance methods, using library APIs, image and color processing, object-oriented I/O, aliasing, pass-by-value for object references, arrays-as-objects, safe pointers, garbage collection.

**Key takeaways**
- `String`, `Color`, `Picture`, and I/O types (`In`, `Out`) show how reference types work in the wild.
- Aliasing is real. `a = b` copies the *reference*, not the object. Mutating via either reference affects the same object.
- Java is pass-by-value. For objects, the *reference* is copied. Reassigning a parameter does not affect the caller.
- Arrays are reference types too. Arrays of objects require a two-step creation: the array, then each element.

**Demos and clients to try**
- Strings and genomics: `PotentialGene.java`  
  https://introcs.cs.princeton.edu/java/31datatype/PotentialGene.java.html
- Color and luminance utilities: `Luminance.java`  
  https://introcs.cs.princeton.edu/java/31datatype/Luminance.java.html
- Image filters: `Grayscale.java`, `Scale.java`, `Fade.java`  
  https://introcs.cs.princeton.edu/java/31datatype/Grayscale.java.html  
  https://introcs.cs.princeton.edu/java/31datatype/Scale.java.html  
  https://introcs.cs.princeton.edu/java/31datatype/Fade.java.html
- I/O utilities: `Cat.java`, `Split.java`, `StockQuote.java`  
  https://introcs.cs.princeton.edu/java/31datatype/Cat.java.html  
  https://introcs.cs.princeton.edu/java/31datatype/Split.java.html  
  https://introcs.cs.princeton.edu/java/31datatype/StockQuote.java.html

More: https://introcs.cs.princeton.edu/java/31datatype/

---

## 3.2 Creating Data Types
**Concepts:** classes, APIs, access modifiers, instance variables, constructors, instance methods, test clients, immutability by example.

**Key takeaways**
- Start from the **API** as a contract for clients. Then implement the class to satisfy the contract.
- Each class should include a **test client** in `main` that exercises every constructor and public method.
- Prefer **immutable** value types unless mutation is essential. Easier to reason about and compose.

**Demos and clients to try**
- Stopwatch: `Stopwatch.java`  
  https://introcs.cs.princeton.edu/java/32class/Stopwatch.java.html
- Histogram: `Histogram.java`  
  https://introcs.cs.princeton.edu/java/32class/Histogram.java.html
- Turtle graphics: `Turtle.java`, with `Ngon.java`, `Koch.java`, `Spiral.java`, `DrunkenTurtle(s).java`  
  https://introcs.cs.princeton.edu/java/32class/Turtle.java.html  
  https://introcs.cs.princeton.edu/java/32class/Ngon.java.html  
  https://introcs.cs.princeton.edu/java/32class/Koch.java.html  
  https://introcs.cs.princeton.edu/java/32class/Spiral.java.html  
  https://introcs.cs.princeton.edu/java/32class/DrunkenTurtle.java.html  
  https://introcs.cs.princeton.edu/java/32class/DrunkenTurtles.java.html
- Complex numbers (immutable): `Complex.java`  
  https://introcs.cs.princeton.edu/java/32class/Complex.java.html
- Mandelbrot viewer: see section examples built on `Complex`  
  https://introcs.cs.princeton.edu/java/32class/

More: https://introcs.cs.princeton.edu/java/32class/

---

## 3.3 Designing Data Types
**Concepts:** encapsulation, immutability, interfaces and subtyping, polymorphism, `this`, overriding `toString`, `equals`, `hashCode`, wrapper types, autoboxing, design-by-contract with exceptions and assertions.

**Key takeaways**
- **Encapsulation:** mark fields `private`. Only expose operations you are willing to support long term. Swap internal representations without breaking clients.
- **Immutability:** make fields `final` and avoid exposing mutable internals. For mutable members, make **defensive copies**.
- **Interfaces over inheritance:** program to an interface, not a concrete class. Use polymorphism with clean contracts.
- **Object contracts:** if you override `equals`, you must also override `hashCode`. Implement an equivalence relation. Provide a helpful `toString`.
- **Design by contract:** use **exceptions** for user-visible precondition violations and **assertions** for internal invariants during development.

**Demos and clients to try**
- Vectors (immutable) with `this` usage: `Vector.java`  
  https://introcs.cs.princeton.edu/java/33design/Vector.java.html
- Function interface and polymorphic clients:  
  `Function.java`, `Square.java`, `GaussianPDF.java`, `FunctionGraph.java`, `RectangleRule.java`  
  https://introcs.cs.princeton.edu/java/33design/Function.java.html  
  https://introcs.cs.princeton.edu/java/33design/Square.java.html  
  https://introcs.cs.princeton.edu/java/33design/GaussianPDF.java.html  
  https://introcs.cs.princeton.edu/java/33design/FunctionGraph.java.html  
  https://introcs.cs.princeton.edu/java/33design/RectangleRule.java.html
- Data-mining sketch and cosine similarity: `Sketch.java`, `CompareDocuments.java`  
  https://introcs.cs.princeton.edu/java/33design/Sketch.java.html  
  https://introcs.cs.princeton.edu/java/33design/CompareDocuments.java.html

More: https://introcs.cs.princeton.edu/java/33design/

---

## 3.4 Case Study — N-Body Simulation
**Concepts:** composing types, physics modeling, separating model (`Body`, `Vector`) from simulation `Universe`, step-wise updates, file formats.

**Demos and clients to try**
- Bouncing balls warm-up: `Ball.java`, `BouncingBalls.java`  
  https://introcs.cs.princeton.edu/java/34nbody/Ball.java.html  
  https://introcs.cs.princeton.edu/java/34nbody/BouncingBalls.java.html
- Gravity simulation: `Body.java`, `Universe.java`  
  https://introcs.cs.princeton.edu/java/34nbody/Body.java.html  
  https://introcs.cs.princeton.edu/java/34nbody/Universe.java.html
- Sample inputs: `2body.txt`, `3body.txt`, `4body.txt`  
  https://introcs.cs.princeton.edu/java/34nbody/2body.txt  
  https://introcs.cs.princeton.edu/java/34nbody/3body.txt  
  https://introcs.cs.princeton.edu/java/34nbody/4body.txt

More: https://introcs.cs.princeton.edu/java/34nbody/

---

## Extra resources on the site
- Java cheatsheet: https://introcs.cs.princeton.edu/java/cheatsheet/
- Code index: https://introcs.cs.princeton.edu/java/code/
- Data files: https://introcs.cs.princeton.edu/java/data/
- Lectures: https://introcs.cs.princeton.edu/java/lectures/

---

## Future-Wesley notes for real-world coding

### API and design
- Treat your **public API** as a contract. Name things carefully. Keep the surface area small. Hide everything else.
- Prefer **composition** to inheritance for code reuse. If you must subclass, be explicit about which methods are safe to override.
- Default to **immutable value objects** for domain concepts. If mutation is required, make it narrow and controlled.

### Object contracts checklist
- If you override `equals`, also override `hashCode` and `toString`.  
- `equals` must be reflexive, symmetric, transitive, non-null friendly.  
- Keep `equals` consistent with what callers rely on for logical equality.  
- Make `hashCode` stable over the lifetime of the object and consistent with `equals`.

### Encapsulation and safety
- Mark fields `private` and `final` where possible.  
- Never leak references to mutable internals. Use **defensive copies** in constructors, setters, and accessors.  
- Avoid aliasing traps. Be cautious when passing collections to other components.

### Errors and invariants
- **Exceptions** for invalid inputs and recoverable conditions at module boundaries.  
- **Assertions** for internal invariants during development. Do not rely on assertions for production logic.  
- Craft clear exception messages that include parameter names and observed values.

### Testing and sanity hooks
- Include a **test client** for each data type that exercises every constructor and public method.  
- Add lightweight property checks. For numerics, test against identities or conservation laws.  
- Log at clear levels and keep logs machine-parsable.

### Performance and memory
- Immutable objects are cheap to share and safe to cache.  
- Be aware of temporary allocations in hot paths.  
- For large arrays of objects, consider storing primitives plus indices, or flyweight patterns, before micro-optimizing.

### I/O and resources
- Prefer explicit resource lifecycles. In modern Java use try-with-resources for streams and readers.  
- Keep parsing and formatting logic near the boundary, not buried in business objects.

### Team readability
- Write `toString` to help debugging. Print key fields in a human-readable single line.  
- Avoid cleverness. Document non-obvious invariants and preconditions right above the method.

---

## Build-your-own pattern: “Creating a data type” template

1) **API**: write a concise class javadoc and method signatures.  
2) **Fields**: `private` and `final` unless mutation is essential.  
3) **Constructors**: validate inputs, establish invariants.  
4) **Methods**: keep them small, single-purpose. Respect preconditions and postconditions.  
5) **Contracts**: implement `equals`, `hashCode`, `toString` when this is a value type.  
6) **Testing**: embed a `main` as a test client that covers all public members.

