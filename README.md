# Generics

A growable array that works for any element type, evolved from Chapter 1's
int-only `DynamicArray`. This is the reference code for Chapter 2 — the classes
here are assembled exactly as the chapter notes build them up, step by step.

## Prerequisites

- JDK 17+

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  src/
    main/
      generics/
        DynamicArray.java   # the growable array, now generic: DynamicArray<T>
        Student.java        # a sample element type: equals, hashCode, Comparable
        ArrayUtils.java     # generic algorithms: swap, indexOfMin, selectionSort
        GpaComparator.java  # an external ordering for Student, by GPA
        Main.java           # a small demo
      practice/             # solutions to the chapter's practice problems
        Card.java           # a value type: equals, hashCode, Comparable
        SuitComparator.java # an external ordering for Card, by suit
        MoreArrayUtils.java # a generic max over a DynamicArray
        PracticeMain.java   # a small demo of the practice solutions
  scripts/
    run.sh                  # compile everything and run the Main demo
    run-practice.sh         # compile everything and run the practice demo
```

## How to compile and run

- `scripts/run.sh` — compiles all source into `out/` and runs the `Main` demo.
- `scripts/run-practice.sh` — compiles all source and runs the `PracticeMain` demo.

There is no build tool and no test suite: testing is introduced later in the
course. The script above is all you need.

## What's here

- `generics.DynamicArray<T>` — Chapter 1's growable array, made generic. It
  stores any reference type, backs itself with `(T[]) new Object[...]`, compares
  elements with `equals` (through `indexOf`), and overloads `remove` by index and
  by value.
- `generics.Student` — a sample element type. Defines value equality by `id`
  (`equals`/`hashCode`) and a natural order by `id` (`Comparable<Student>`),
  keeping the two consistent.
- `generics.ArrayUtils` — generic algorithms that touch a `DynamicArray` only
  through its public operations: `swap` (unbounded), plus `indexOfMin` and
  `selectionSort` in two forms each — by natural order
  (`<T extends Comparable<T>>`) and by a supplied `Comparator<T>`.
- `generics.GpaComparator` — an ordering of students by GPA, supplied from
  outside the `Student` type.
- `generics.Main` — a runnable demo: a roster of students sorted by id and by
  GPA, plus the `DynamicArray<Integer>` `remove` overload trap.

## Practice solutions

The `practice` package holds reference solutions to the chapter's practice
problems, kept separate from the chapter's own code:

- `practice.Card` — a fresh value type: value equality by rank and suit
  (`equals`/`hashCode`) and a natural order by rank, then suit
  (`Comparable<Card>`), kept consistent with each other.
- `practice.SuitComparator` — an ordering of cards by suit, then rank, supplied
  from outside `Card`.
- `practice.MoreArrayUtils` — a generic `max` over a `DynamicArray`, in two
  forms: by natural order (`<T extends Comparable<T>>`) and by a supplied
  `Comparator<T>`.
- `practice.PracticeMain` — a runnable demo showing the two orderings produce
  different winners from the same hand.
