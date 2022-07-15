/**
 * Define Grid properties.
 */
export interface GridProps{
  text: string
  clazz: string
  link: string
  onClick: (link: string, clazz: string) => void
}

/**
 * A functional component of Grid
 */
export default function Grid (
  { text, clazz, link, onClick }: GridProps
) {
  return (
    <button className={clazz} onClick={() => onClick(link, clazz)}>
      {text}
    </button>
  )
}
