// Création du dossier "Tools"
folder('Tools') {
    description('Folder for miscellaneous tools.')
}
// Création du job "SEED" dans le dossier "Tools"
// Ce job délègue la création d'autres jobs via un script DSL externe.
job('Tools/SEED') {
    parameters {
        stringParam('GITHUB_NAME', '', 'GitHub repository owner/repo_name')
        stringParam('DISPLAY_NAME', '', 'Display name for the job')
    }
    wrappers {
        preBuildCleanup()
    }
    steps {
        dsl {
            // Appelle le script externe pour générer d'autres jobs.
            external('job_dsl.groovy')
            // Optionnel: désactive la sandbox (attention aux risques de sécurité)
            useScriptSecurity(false)
        }
    }
}

// Création du job "clone-repository" dans le dossier "Tools"
job('Tools/clone-repository') {
    parameters {
        stringParam('GIT_REPOSITORY_URL', '', 'Git URL of the repository to clone')
    }
    wrappers {
        preBuildCleanup()
    }
    steps {
        shell("rm -rf * && git clone ${GIT_REPOSITORY_URL}")
    }
}
