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
            saveProject: saveProject
        };

        function saveProject(project) {
            $resource('rest/user/userID/project', {userID:'@id'}).save(project);
        }
    }
})();



