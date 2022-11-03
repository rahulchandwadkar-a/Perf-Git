#!/usr/bin/env groovy
node {

    stage('Initialise') {
        /* Checkout the scripts */
        checkout scm: [
                $class: 'GitSCM',
                userRemoteConfigs: [
                        [
                                url: "https://github.com/rahulchandwadkar-a/Perf-Git.git",
                                credentialsId: "rahulchandwadkar-a"
                        ]
                ],
                branches: [[name: "main"]]
        ], poll: false
    }

    stage('execution start') {
        echo "execution start"
        //echo "${octoperf_test_value}"
    }

    stage('Execute Performance Tests') {
        dir("${WORKSPACE}/scripts") {
            bat "C:/My-D/apache-jmeter-5.4.1/bin/jmeter.bat -n -t Facility-Managment-Perf-git.jmx -l result-Facility-Managment-Perf-git.jtl //-Joptestvalue=${octoperf_test_value}"
        }
    }

    stage('Analyse Results') {
        echo "Analyse results"
    }
}
