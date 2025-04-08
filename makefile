# Compilation standard
all:
    gcc -o app main.c

# Nettoyage léger
clean:
    rm -f *.o

# Nettoyage complet
fclean: clean
    rm -f app

# Lancement des tests
tests_run:
    ./app --testapp