IA1_SystemeExpert
=================

TP1 IA M1-S1

Utilisation:
  Au choix: soit en exécutant le .jar en ligne de commande
    soit depuis eclipse en lançant la classe de test MainTests, en utilisant la variable args pour simuler le passage d'arguments

Options:
  -file [file path]: spécifie le fichier de données à lire, par défaut "res/universe.txt"
  -conflict [premisse | value]: spécifie la méthode de résolution de conflits
                            premisses: choisit la règle ayant le plus de prémisses (par défaut)
                            value: choisit la règle dont les prémisses sont les plus récentes
  -strategy [avant | arriere]: spécifie le type de chainage à effectuer (par défaut: arriere)
  -logLevel [all | rules | none]: spécifie le niveau de la trace à afficher
                            all: affiche toutes les actions effectuées
                            rules: affiche uniquement les actions et tests concernant les règles
                            none: n'affiche rien
                            
Données:
  Le dossier "res" contient:
    -un exemple de fichier de données: universe.txt
    -un fichier décrivant la syntaxe utilisée: syntax.txt
