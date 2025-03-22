package org.devops

class DockerComposeManager {
    String dockerComposeFile

    DockerComposeManager(String dockerComposeFile) {
        this.dockerComposeFile = dockerComposeFile
    }

    String getCurrentImageVersion(String subProject) {
        return sh(script: "grep -oP '${subProject}:\\K\\d+\\.\\d+\\.\\d+' ${dockerComposeFile}", returnStdout: true).trim()
    }

    void updateImageVersion(String subProject String newVersion) {
        sh "sed -i '' 's/${subProject}:.*/${subProject}:${newVersion}/' ${dockerComposeFile}"
    }

    void commitAndPush(String subProject ,String BRANCH_NAME) {
        sh "git add ${dockerComposeFile}"
        sh "git commit -m \"Update project $sub{Project} Docker image version to ${newVersion}\""
        sh "git push origin HEAD:${BRANCH_NAME}"
    }
}
