package Textos;

public class A19_CORS {

    // =====================================
    // Cross-Origin Resurce Sharing - CORS
    // =====================================

    /*
    * O que é?
    *
    * CORS é um mecanismo de segurança do navegador que controla
    * quais origens externas podem acessar sua API.
    *
    * CORS nada mais é que "compartilhamento de recursos
    * de origem cruzada".
    *
    * O CORS quebra a limitação imposta pela politica de segurança
    * chamada Same-origin Policy.
    *
    * Dois sites só podem compartilhar recursos se estiverem no mesmo
    * domínio,com o mesmo protocolo (http ou https), na mesma porta e
    * com o mesmo endereço.
    *
    * CORS é uma proteção do NAVEGADOR, não do servidor.
    * → Postman/Insomnia não sofrem bloqueio de CORS (não são navegadores)
    * → Se funciona no Postman mas falha no frontend = problema de CORS.
    *
    * ============================================
    * COMO CONFIGURAR CORS GLOBALMENTE (app.yml)
    * ============================================
    *
    * No application.yml criamos uma propriedade customizada
    * com as origens permitidas separadas por vírgula:
    *
    * cors:
    *   originPatterns: http://localhost:8080,http://localhost:3000,https://meusite.com
    *
     * → Fazemos isso tanto no application.yml principal
    *   quanto no application.yml de test (para os testes de integração)
    * → Separamos por vírgula pois vamos fazer um .split(",") no código
    * → Assim podemos adicionar/remover origens sem mexer no código Java
    *
    * ============================================
    * CONFIGURAÇÃO GLOBAL NA CLASSE WebConfig
    * ============================================
    *
    * @Value("${cors.originPatterns}")      → injeta o valor do yml
    * private String corsOriginPatterns;    → armazena as origens como String
    *
    * @Override
    * public void addCorsMappings(CorsRegistry registry) {
    *
    *
    *     → Transforma a String "http://localhost:8080,http://localhost:3000"
    *       em um array ["http://localhost:8080", "http://localhost:3000"]
    *
    *    var allowedOrigins = corsOriginPatterns.split(",");
    *
    *
    *     → "/**" = aplica essa regra CORS para TODOS os endpoints da API
    *       Ex: /api/usuarios, /api/produtos, tudo liberado
    *
    *    registry.addMapping("/**")
    *
    *       → Define QUAIS origens (sites) podem acessar a API
    *         Só as origens listadas no yml serão aceitas
    *
    *            .allowedOrigins(allowedOrigins)
    *
    *       → Define quais verbos HTTP são permitidos
    *         OPTIONS é obrigatório → é o "Preflight" (navegador bate na porta antes de entrar)
    * ️         Use OU essa linha OU .allowedMethods("*"), nunca as duas!
    *         A segunda chamada sobrescreve a primeira.
    *
    *            .allowedMethods("GET","POST","PUT","DELETE","PATCH","OPTIONS")
    *
    *       → Permite envio de credenciais na requisição
    *         (cookies, tokens de autenticação, headers de sessão)
    *          Com allowCredentials(true) você NÃO pode usar
    *          allowedOrigins("*") — o Spring lança erro!
    *          Precisa especificar as origens explicitamente.
    *
    *            .allowCredentials(true);
    * }
    *
    * ============================================
    * FORMAS DE CONFIGURAR CORS
    * ============================================
    *
    * 1. GLOBAL (WebConfig) → se aplica a toda a API ✅ mais usado em produção
    *
    * 2. Por Controller → regras específicas por endpoint
    *    @CrossOrigin(origins = "http://localhost:8080")
    *    → útil para liberar apenas um endpoint específico.
    *
    * 3. Por verbo HTTP → controle ainda mais fino por metodo
    *
    * ============================================
    * O PROBLEMA QUE CORS RESOLVE
    * ============================================
    *
     * Sem CORS:
     * Site malicioso (evil.com)
     *   ↓
     * Chama sua API (meusite.com/api/usuarios)
     *   ↓
     * Rouba dados dos seus usuários ✅
     *
     * Com CORS:
     * Site malicioso (evil.com)
     *   ↓
     * Chama sua API (meusite.com/api/usuarios)
     *   ↓
     * Navegador verifica → evil.com não está na lista
     *   ↓
     * ❌ BLOQUEADO
     *
     * ============================================
     * FLUXO DO PREFLIGHT (OPTIONS)
     * ============================================
     *
     * Frontend (localhost:3000) chama API (localhost:8080)
     *   ↓
     * Navegador envia requisição OPTIONS automaticamente
     * perguntando: "posso acessar essa API?"
     *   ↓
     * Servidor responde: "Sim, localhost:3000 pode acessar"
     *   ↓
     * Navegador libera a requisição real ✅
     *
     * ============================================
     * Testes de Integração
     * ============================================
     *
     * Criamos uma "Interface" chamada "TesteConfigs".
     *
     * Criamos um dto chamado "Persondto" dentro de
     * "teste/java/Santos.David/integrationtest/dto".
     */
}
