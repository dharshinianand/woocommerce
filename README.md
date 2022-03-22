# woocommerce
Automation script to add products and order them.
## Pre-requisite:
Download Java 17.0.2 2022-01-18 LTS from [_here_](https://download.oracle.com/java/17/latest/jdk-17_windows-x64_bin.exe) for windows and install it.
>Example:
setting system properties if not done already
java installed on "C:\Program Files\Java\jdk-17.0.2" on windows. 
add java bin directory in path variable "C:\Program Files\Java\jdk-17.0.2\bin"
create an environment variable java_home if not already available. 
JAVA_HOME=C:\Program Files\Java\jdk-17.0.2

## Update config.properties
Change admin url, login name, password in config.properties
url: http://127.0.0.1/admin
After products created change shop url in config.properties. 
url : http://127.0.0.1/wordpress/shop

Note: URL should navigate directly to shop the products page otherwise the script won't proceed further.








