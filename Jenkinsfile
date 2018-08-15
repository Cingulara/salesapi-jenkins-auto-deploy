def CONTAINER_NAME="salesapi"
def CONTAINER_TAG="latest"
def CONTAINER_DB_NAME="salesapidb"
def CONTAINER_DB_TAG="latest"
def OPENSHIFT_PROJECT_NAME="salesapi"
def OPENSHIFT_IP="172.30.1.1:5000"

node {

    stage('Checkout') {
        checkout scm
    }

    stage('Build') {
        bat "C:\\apache-maven-3.5.3\\bin\\mvn clean package"
    }

    stage('SonarQube'){
        try {
            bat "C:\\apache-maven-3.5.3\\bin\\mvn sonar:sonar"
        } catch(error){
            echo "The sonar server could not be reached ${error}"
        }
     }

    stage("Image Prune"){
        imagePrune(CONTAINER_NAME)
        imagePrune(CONTAINER_DB_NAME)
    }

    stage('DB Image Build'){
        imageBuild(CONTAINER_DB_NAME, CONTAINER_DB_TAG, "database\\")
    }

    stage('Application Image Build'){
        imageBuild(CONTAINER_NAME, CONTAINER_TAG, ".")
    }

    stage('Push DB to OpenShift Registry'){
        withCredentials([usernamePassword(credentialsId: 'openshift-docker-registry-account', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
            pushToImage(OPENSHIFT_IP,OPENSHIFT_PROJECT_NAME, CONTAINER_DB_NAME, CONTAINER_DB_TAG, USERNAME, PASSWORD)
        }
    }

    stage('Push App to OpenShift Registry'){
        withCredentials([usernamePassword(credentialsId: 'openshift-docker-registry-account', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
            pushToImage(OPENSHIFT_IP,OPENSHIFT_PROJECT_NAME, CONTAINER_NAME, CONTAINER_TAG, USERNAME, PASSWORD)
        }
    }
}

def imagePrune(containerName){
    try {
        bat "\"C:\\Program Files\\Docker\\Docker\\Resources\\bin\\docker\" image prune -f"
        bat "\"C:\\Program Files\\Docker\\Docker\\Resources\\bin\\docker\" stop $containerName"
    } catch(error){}
}

def imageBuild(containerName, tag, pathToDockerfile){
    bat "\"C:\\Program Files\\Docker\\Docker\\Resources\\bin\\docker\" build -t $containerName:$tag  -t $containerName --pull --no-cache $pathToDockerfile"
    echo "$containerName Image build complete"
}

def pushToImage(openshiftIP, projectName, containerName, tag, dockerUser, dockerPassword){
    bat "\"C:\\Program Files\\Docker\\Docker\\Resources\\bin\\docker\" login -u $dockerUser -p $dockerPassword $openshiftIP"
    bat "\"C:\\Program Files\\Docker\\Docker\\Resources\\bin\\docker\" tag $containerName:$tag $openshiftIP/$projectName/$containerName:$tag"
    bat "\"C:\\Program Files\\Docker\\Docker\\Resources\\bin\\docker\" push $openshiftIP/$projectName/$containerName:$tag"
    echo "$containerName Image push to OpenShift project complete"
}
