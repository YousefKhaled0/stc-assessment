package main

import (
	"bufio"
	"fmt"
	"log"
	"os"
	"strings"
)

func main() {

	str, err := bufio.NewReader(os.Stdin).ReadString('\n')

	if err != nil {

		log.Fatal(err)
	}

	fmt.Println(reverseSubStrings(str))
}

type StackNode struct {
	value rune
	next  *StackNode
}

type Stack struct {
	head *StackNode
}

func (stack *Stack) peek() rune {

	return stack.head.value
}

func (stack *Stack) push(c rune) {

	node := &StackNode{
		c,
		stack.head,
	}

	stack.head = node
}

func (stack *Stack) pop() rune {

	val := stack.head.value

	stack.head = stack.head.next

	return val
}

func reverseSubStrings(s string) string {

	opened := 0

	stack := Stack{}

	var sb strings.Builder

	for _, c := range s {

		if c == '(' {

			opened++
			stack.push(c)

			continue
		} else if c == ')' {

			opened--

			var tmp strings.Builder

			for stack.head != nil && stack.peek() != '(' {

				tmp.WriteRune(stack.pop())
			}

			stack.pop()

			if opened > 0 {

				for _, cTmp := range tmp.String() {

					stack.push(cTmp)
				}

			} else {

				sb.WriteString(tmp.String())
			}

			continue
		}

		if opened > 0 {

			stack.push(c)
		} else {

			sb.WriteRune(c)
		}
	}

	return sb.String()
}
