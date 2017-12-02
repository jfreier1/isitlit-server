 (defproject isitlit-server "0.1.0-SNAPSHOT"
   :description "FIXME: write description"
   :dependencies [[org.clojure/clojure "1.8.0"]
                  [metosin/compojure-api "1.1.11"]
                  [korma "0.4.3"]
                  [org.clojure/java.jdbc "0.6.1"]
                  [mysql/mysql-connector-java "5.1.25"]
                  [ring "1.6.3"]
                  [puppetlabs/ring-middleware "1.0.0"]
                  [ring/ring-json "0.4.0"]
                  [javax.servlet/servlet-api "2.5"]
                  [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]]
   :plugins [[lein-ring "0.12.1"]]
   :ring {:handler isitlit-server.handler/app}
   :uberjar-name "server.jar"
   :profiles {:dev {:dependencies [[javax.servlet/javax.servlet-api "3.1.0"]]
                   :plugins [[lein-ring "0.12.0"]]}})
