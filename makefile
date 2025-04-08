all:
		gcc -o app main.c

clean:
		rm -f *.o

fclean: clean
		rm -f app

tests_run:
		./app --testapp
