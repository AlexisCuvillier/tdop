// Récupération des paramètres passés au job SEED
def githubName = binding.variables['GITHUB_NAME']
def displayName = binding.variables['DISPLAY_NAME']

println "GITHUB_NAME: ${githubName}"
println "DISPLAY_NAME: ${displayName}"

// Génération du lien GitHub et du repository SCM à partir du nom
def githubUrl = "https://github.com/${githubName}.git"
def projectUrl = "https://github.com/${githubName}"

// Création du job
job(displayName) {
    description("Job auto-généré pour ${githubName}")

    // Lien vers le projet GitHub
    properties {
        githubProjectUrl(projectUrl)
    }

    // Configuration SCM Git
    scm {
        git {
            remote {
                url(githubUrl)
            }
            branches('*/main')
        }
    }

    // Déclencheurs
    triggers {
        scm('* * * * *') // Toutes les minutes
    }

    // Nettoyage du workspace avant build
    wrappers {
        preBuildCleanup()
    }
    // Étapes shell à exécuter
    steps {
        shell('make fclean')
        shell('make')
        shell('make tests_run')
        shell('make clean')
    }
}
