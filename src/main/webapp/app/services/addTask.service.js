/**
 * Created by Pierre on 17/04/2015.
 */



(function () {
    angular
        .module('JarvisApp')
        .factory('Tasks', tasks);


    tasks.$inject = ['$resource'];

    function tasks($resource) {
        return {
            saveTask: saveTask
        };

        function saveTask(task,projectID) {
            console.log(projectID);
            $resource('rest/user/userID/project/'+projectID+'/task').save(task);
        }
    }
})();
