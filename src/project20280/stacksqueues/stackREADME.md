Q2
QueueMaker() {
    new stack s1
    new stack s2

    enqueue(e) {
        s1.push(e)
    }

    dequeue() {
        while (s1.isEmpty() == false) {
        s2.push(s1.pop)
        }
        released = s2.pop

        while (s2.isEmpty() == false) {
        s1.push(s2.pop)
        }

        return released
    }

}

Q3
reverse (s1) {
    new Stack s2
    new Stack s3

    while (s1.isEmpty() == false) {
        s2.push(s1.pop)
    }

    while (s2.isEmpty() == false) {
    s3.push(s2.pop)
    }

    while (s3.isEmpty() == false) {
    s1.push(s3.pop)
    }
}
