/**
 * Created by Pierre on 08/04/2015.
 */


(function () {
    angular
        .module('JarvisApp')
        .factory('jarvisAppConnexion', jarvisAppConnexion);

    function jarvisAppConnexion($http) {
        return {
            connection: function (user) {
                return $http.post('rest/connect', user);
            }
        };
    }
})();

