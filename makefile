all:
		gcc -o app main.c

clean:
		rm -f *.o

fclean: clean
		rm -f app

# Makefile for a simple C project
tests_run:
		./app --testapp
