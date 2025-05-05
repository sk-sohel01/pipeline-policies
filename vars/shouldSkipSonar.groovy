def call(String repoName) {
    def rawURL = 'https://raw.githubusercontent.com/sk-sohel01/pipeline-policies/main/sonar-skip-repos/list.txt'
    try {
        def skipList = new URL(rawURL).text.readLines().collect { it.trim() }
        def shouldSkip = skipList.contains(repoName)
        echo shouldSkip 
            ? "✅ Skipping SonarQube for whitelisted repo: ${repoName}" 
            : "🔍 SonarQube required for: ${repoName}"
        return shouldSkip
    } catch (Exception e) {
        echo "⚠️ Could not load skip list: ${e.message}"
        return false
    
}
