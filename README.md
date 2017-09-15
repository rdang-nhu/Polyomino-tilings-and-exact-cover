A polyomino is set of squares in the plane that are connected along edges. Dually, it is a finite connected induced subgraph of the integer grid. Given a set S of polyominoes and a polyomino P , a polyomino tiling of P with S is a partition of P by the polyominoes of S.
The goal of this project is to answer as efficiently as possible questions like:
  -generate all polyominoes of a given area up to isometries,
  -find all polyomino tilings of P with S,
  -find all possible polyomino tilings of a rectangle by all polyominoes of a given area,
  -find dilates of P that can be tiled by copies of P, etc.
In general, polyomino tiling problems are known to be computationally difficult. This project aims at implementing an efficient backtracking technique for these problems. In fact we implemented an algorithm to solve any exact cover problem, a naive one, but also Dancing Links algorithm. Finally we applied this algorithm to solve the N-Queen problem.
