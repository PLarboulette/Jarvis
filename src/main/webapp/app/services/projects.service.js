/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    angular
        .module('JarvisApp')
        .factory('Projects', projects);


    projects.$inject = ['$resource'];

    function projects($resource) {

        return {
            saveProject: saveProject,
            updateProject : updateProject,
            getProjects : getProjects
        };

        function saveProject(project) {
            $resource('rest/user/userID/project').save(project);
        }

        function updateProject (project,projectID) {
            $resource('rest/user/userID/project/'+projectID).save(project);
        }

        function getProjects () {
         return $resource('rest/user/userID/project').query();

        }

    }
})();



