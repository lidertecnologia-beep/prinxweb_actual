/* eslint-disable import/no-anonymous-default-export */
export default function (req, res) {
  const { au } = req.cookies;
  const { pers } = req.cookies;

  if (au && pers) {
    return res.json({ authSucces: au, personal: pers });
  }
  return res.status(404).json({ authSucces: false, personal: null });
}
