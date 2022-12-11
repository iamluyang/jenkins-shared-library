package com.hyperctrl.jenkins.shared.library.service.api

/**
 * https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-container-registry#pushing-container-images
 */
interface IDockerService {

    /**
     * sign in to the Container registry
     * @param dockerCredentialsId
     */
    void login()

    /**
     *
     * @param dockerImageOwner
     * @param dockerImageName
     * @param dockerImageTag
     * @param dockerImageLabels
     */
    void build(String dockerImageOwner, String dockerImageName, String dockerImageTag, Map<String, String> dockerImageLabels)

    /**
     *
     * @param dockerImageOwner
     * @param dockerImageName
     * @param dockerImageTag
     */
    void push(String dockerImageOwner, String dockerImageName, String dockerImageTag)

    /**
     *
     * @param dockerImageOwner
     * @param dockerImageName
     * @param sha
     */
    void pushBySha(String dockerImageOwner, String dockerImageName, String sha)

    /**
     *
     * @param dockerImageOwner
     * @param dockerImageName
     * @param dockerImageTag
     */
    void rmi(String dockerImageOwner, String dockerImageName, String dockerImageTag)

    /**
     *
     */
    void imagePrune()

    /**
     *
     * @param dockerImageOwner
     * @param dockerImageName
     * @param dockerImageTag
     */
    void inspect(String dockerImageOwner, String dockerImageName, String dockerImageTag)

    /**
     *
     */
    void images();
}