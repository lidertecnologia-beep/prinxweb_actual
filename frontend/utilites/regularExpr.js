const regularExpr = {
  repeatedCharacter: /([a-zA-Z.|,-_<>{}])\1{3,}/,
  repeatedNumeroDetailField: /([1-9])\1{9,}/
}

export const soloNumeros = (str) => /^\d+$/.test(str)

export default regularExpr
