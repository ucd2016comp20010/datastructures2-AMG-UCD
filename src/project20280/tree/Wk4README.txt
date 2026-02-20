Q2
    itterate through children of positions
    if isInternal → recursive call
    if isExternal → return 1
    add all results
    return sum

    int numExternal(p) {
        int count = 0;
        for (Position<E> curr: children(p)) {
            if (isExternal(curr)) {
                count++;
                }
            else if (isInternal(curr)) {
                count += numExternal(curr);
            }
        }
        return count;
    }
Q3
    itterate through children of positions
    if isInternal → recursive call
    if isExternal and left → return 1
    if isExternal and right → return 0
    add all results
    return sum
Q4
    preorder:
         E
       /   \
      X     F
     / \   / \
    A   M U   N

    inorder:
         N
       /   \
      A     U
     / \   / \
    E   X M   F

    postorder
         M
       /   \
      X     U
     / \   / \
    E   A F   N
Q5
    itterate through children of position
    if isInternal return recursive call + 1
    if isExternal return 1
    add all results
    return sum
